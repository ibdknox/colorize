(ns colorize.core
  "A set of functions to wrap strings in ansi-colors.")

(def ansi-colors {:reset "[0m"
                  :default "[39m"
                  :white   "[37m"
                  :black   "[30m"
                  :red     "[31m"
                  :green   "[32m"
                  :blue    "[34m"
                  :yellow  "[33m"
                  :magenta "[35m"
                  :cyan    "[36m"})

(defn ansi
  "Get the ansi code for a specific color"
  [code]
  (str \u001b (get ansi-colors code (:reset ansi-colors))))

(defn color 
  "Wrap the given strings in the provided color. Color should be
  a keyword and can be any of the following:
  
  [:reset :default :white :black :red :green :blue :yellow :magenta :cyan].

  Each of these also has a function created for it: (cyan \"woohoo\")"
  [code & s]
  (str (ansi code) (apply str s) (ansi :reset)))

(defmacro create-colors []
  (apply list 'do 
         (for [k (keys ansi-colors)]
           (let [code (ansi k)
                 reset (ansi :reset)]
             `(defn ~(symbol (name k)) [& s#] 
                (str ~code (apply str s#) ~reset))))))

(create-colors)

