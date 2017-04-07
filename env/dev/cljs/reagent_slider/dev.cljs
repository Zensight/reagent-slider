(ns ^:figwheel-no-load reagent-slider.dev
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [reagent.core :as reagent]
            [reagent-slider.core :as core]
            [figwheel.client :as figwheel :include-macros true]))

(enable-console-print!)

(def index->time {0 "today"
                  1 "1 wk"
                  2 "2 wks"
                  3 "1 mo"
                  4 "2 mos"
                  5 "6 mos"
                  6 "1 year"})

(defn wrapper-component [model cback]
  [core/tooltipped-range-component
   {:min 0
    :max (dec (count index->time))
    :value [(:min @model) (:max @model)]
    :marks index->time
    :onAfterChange (fn [_] (cback))
    :onChange (fn [[v1 v2]]
                (reset! model {:min (min v1 v2)
                               :max (max v1 v2)}))
    :tipFormatter #(index->time %)}])

(defn boot-demo-app []

  (let [model (reagent/atom {:min 2 :max 3})
        active-model (reagent/atom @model)
        update! #(reset! active-model @model)]

    (reagent/render [:div {:style {:width 290}}
                     [:button {:on-click #(do
                                            (reset! model {:min 1 :max 5})
                                            (update!))} "switch!"]
                     [wrapper-component model update!]](.getElementById js/document "app"))

    (add-watch active-model :watcher (fn [_ _ _ _] (println @active-model)))))

(figwheel/watch-and-reload
  :websocket-url "ws://localhost:3450/figwheel-ws"
  :jsload-callback boot-demo-app)

(boot-demo-app)
