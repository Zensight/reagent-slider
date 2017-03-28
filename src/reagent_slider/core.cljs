(ns reagent-slider.core
    (:require [reagent.core :as reagent]
              [co.zensight.react-slider]))

(def slider-component (reagent/adapt-react-class js/ReactSlider))
(def range-component (reagent/adapt-react-class (.. js/ReactSlider -Range)))
(def tooltip-component (reagent/adapt-react-class js/ReactTooltip))
(def tooltipped-range-component (reagent/adapt-react-class
                                  (.createSliderWithTooltip js/ReactSlider (.. js/ReactSlider -Range))))
