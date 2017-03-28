(ns reagent-slider.prod
  (:require [reagent-slider.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))
