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

        model (reagent/atom {:min 2 :max 3})]

    (reagent/render [:div {:style {:width 290}}

                     [:button {:on-click #(reset! model {:min 1 :max 5})} "switch!"]

                     [core/tooltipped-range-component
                      {:min 0
                       :max (dec (count index->time))
                       :defaultValue [(:min @model) (:max @model)]
                       :marks index->time
                       :onAfterChange (fn [[v1 v2]]
                                        (reset! model {:min (min v1 v2)
                                                       :max (max v1 v2)}))
                       :tipFormatter #(index->time %)}]](.getElementById js/document "app"))

    (add-watch model :watcher (fn [_ _ _ _] (println @model)))))

(figwheel/watch-and-reload
  :websocket-url "ws://localhost:3450/figwheel-ws"
  :jsload-callback boot-demo-app)

(boot-demo-app)
