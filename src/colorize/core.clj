(ns colorize.core
  "A set of functions to wrap strings in ansi-colors."
  (:use [clojure.pprint :only [pprint]]))

(def ansi-colors {:reset "[0m"
                  :default "[39m"
                  :white   "[37m"
                  :black   "[30m"
                  :red     "[31m"
                  :green   "[32m"
                  :blue    "[34m"
                  :yellow  "[33m"
                  :magenta "[35m"
                  :cyan    "[36m"
                  :black-bg "[40m"
                  :red-bg "[41m"
                  :green-bg "[42m"
                  :yellow-bg "[43m"
                  :blue-bg "[44m"
                  :magenta-bg "[45m"
                  :cyan-bg "[46m"
                  :white-bg "[47m"
                  :bold "[1m"
                  :italic "[3m"
                  :underline "[4m"
                  :inverse "[7m"
                  :strikethrough "[9m"})

(defn ansi
  "Get the ansi code for a specific color"
  [code]
  (str \u001b (get ansi-colors code (:reset ansi-colors))))

(defn color 
  "Wrap the given strings in the provided color. Color should be
  a keyword and can be any of the following:
  
  [:reset :default :white :black :red :green :blue :yellow :magenta :cyan
   :black-bg :red-bg :green-bg :yellow-bg :blue-bg :magenta-bg :cyan-bg :white-bg
   :underline :italic :bold :strikethrough :inverse].

  Each of these also has a function created for it: (cyan \"woohoo\")"
  [code & s]
  (str (ansi code) (apply str s) (ansi :reset)))

(defn show-all []
  (let [all-colors (apply juxt (for [cur (keys ansi-colors)]
                                 (fn [input]
                                   [cur (color cur input)])))]
    (pprint (into (sorted-map) (all-colors "test")))))

(defmacro create-colors []
  (apply list 'do 
         (for [k (keys ansi-colors)]
           (let [code (ansi k)
                 reset (ansi :reset)]
             `(defn ~(symbol (name k)) [& s#] 
                (str ~code (apply str s#) ~reset))))))

(create-colors)

