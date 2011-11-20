# colorize

A simple library to color string output with ansi-codes.

## Usage

```clojure
(use 'colorize.core)

(color :red "hey " "what's up")
(red "hi!")
(cyan "what's " (green "going on?"))
(cyan-bg "blah")
(underline (bold "cool"))

;;Available commands:
[reset default white black red green blue yellow magenta cyan
 black-bg red-bg green-bg yellow-bg blue-bg magenta-bg cyan-bg white-bg
 underline italic bold strikethrough inverse]
```

## License

Copyright (C) 2011 Chris Granger

Distributed under the Eclipse Public License, the same as Clojure.
