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

(defn wrapper-component [internal-value external-value opts]
  [core/tooltipped-range-component
   (merge
    {:value [(:min @internal-value) (:max @internal-value)]
     :onAfterChange (fn [_] (reset! external-value @internal-value))
     :onChange (fn [[v1 v2]]
                 (reset! internal-value {:min (min v1 v2)
                                         :max (max v1 v2)}))}
    opts)])

(defn boot-demo-app []

  (let [model (reagent/atom {:min 2 :max 3})
        slider-model (reagent/atom @model)]

    (reagent/render [:div {:style {:width 290}}
                     [:button {:on-click #(do
                                            (reset! slider-model {:min 1 :max 5})
                                            (reset! model @slider-model))} "switch!"]
                     [wrapper-component
                      slider-model
                      model
                      {:min 0
                       :max (dec (count index->time))
                       :marks index->time
                       :tipFormatter #(index->time %)}]]
                    (.getElementById js/document "app"))

    (add-watch model :watcher (fn [_ _ _ _] (println @model)))))

(figwheel/watch-and-reload
  :websocket-url "ws://localhost:3450/figwheel-ws"
  :jsload-callback boot-demo-app)

(boot-demo-app)
