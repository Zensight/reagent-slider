(ns ^:figwheel-no-load reagent-slider.dev
  (:require-macros [reagent.ratom :refer [reaction]])
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
                     6 "1 year"}

        defaults [2 3]
        model (reagent/atom defaults)
        output (reaction (let [[v1 v2] @model]
                           {:min (index->time (min v1 v2))
                            :max (index->time (max v1 v2))}))]

    (reagent/render [:div {:style {:width 290}}
                     [core/tooltipped-range-component
                      model
                      {:min 0
                       :max (dec (count index->time))
                       :marks index->time
                       :tipFormatter #(index->time %) ;;has to be a function
                       :defaultValue defaults}]](.getElementById js/document "app"))

    (add-watch model :watcher (fn [_ _ _ _] (println @output)))))

(figwheel/watch-and-reload
  :websocket-url "ws://localhost:3450/figwheel-ws"
  :jsload-callback boot-demo-app)

(boot-demo-app)
