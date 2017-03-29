(ns reagent-slider.core
    (:require [reagent.core :as reagent]
              [co.zensight.react-slider]))

(defn- slider [clazz v opts]
  [(reagent/adapt-react-class clazz)
   (assoc opts :on-change #(reset! v %))])

(defn range-component [values opts]
  (slider (.. js/ReactSlider -Range) values opts))

(defn tooltipped-range-component [values opts]
  (slider (.createSliderWithTooltip js/ReactSlider (.. js/ReactSlider -Range)) values opts))

(defn slider-component [v opts]
  (slider js/ReactSlider v opts))

(defn tooltipped-slider-component [v opts]
  (slider (.createSliderWithTooltip js/ReactSlider (.. js/ReactSlider -Range)) v opts))
