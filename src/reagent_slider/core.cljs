(ns reagent-slider.core
    (:require [reagent.core :as reagent]
              [co.zensight.react-slider]))

(def range-component (reagent/adapt-react-class (.. js/ReactSlider -Range)))

(def tooltipped-range-component
  (reagent/adapt-react-class
    (.createSliderWithTooltip js/ReactSlider (.. js/ReactSlider -Range))))

(def slider-component (reagent/adapt-react-class js/ReactSlider))

(def tooltipped-slider-component
  (reagent/adapt-react-class
    (.createSliderWithTooltip js/ReactSlider js/ReactSlider)))
