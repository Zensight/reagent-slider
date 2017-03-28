(ns ^:figwheel-no-load reagent-slider.dev
  (:require [reagent.core :as reagent]
            [reagent-slider.core :as core]
            [figwheel.client :as figwheel :include-macros true]))

(enable-console-print!)

(defn boot-demo-app []

  (let [index->time {0 "today"
                     1 "1 wk"
                     2 "2 wks"
                     3 "1 mo"
                     4 "2 mos"
                     5 "6 mos"
                     6 "1 year"}]

    (reagent/render [:div {:style {:width 290}}
                     [core/tooltipped-range-component {:min 0
                                                       :max (dec (count index->time))
                                                       :marks index->time
                                                       :tipFormatter #(get index->time %) ;;has to be a function
                                                       :defaultValue [0 (dec (count index->time))]}]

                     [:div [:p {:dangerouslySetInnerHTML {:__html "&nbsp;"}}] [:p {:dangerouslySetInnerHTML {:__html "&nbsp;"}}]]

                     [core/tooltipped-range-component {:min 0
                                                       :max 21
                                                       :marks
                                                       {0 "0"
                                                        5 "5"
                                                        10 "10"
                                                        15 "15"
                                                        21 "∞"}
                                                       :tipFormatter #(if (< % 21) (str %) "∞")
                                                       :defaultValue [0 21] }]] (.getElementById js/document "app"))))

  (figwheel/watch-and-reload
    :websocket-url "ws://localhost:3450/figwheel-ws"
    :jsload-callback boot-demo-app)

  (boot-demo-app)
