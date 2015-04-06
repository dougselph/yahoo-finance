# yahoo-finance

A Clojure library designed to parse Yahoo Finance.

## Usage

[![Clojars Project](http://clojars.org/yahoo-finance/latest-version.svg)](http://clojars.org/yahoo-finance)

```clojure
> (def companies (get-all-companies))
> (clojure.pprint/pprint (first companies))
{:code "3076",
 :exchange "東証1部",
 :name "あい　ホールディングス(株)",
 :description "防犯カメラシステム運営・カッティング機器等情報機器・カード発行機・建設設計が４本柱"}
nil
> (map #(:code %) companies)
("3076" "7013" "4812" "9753" "7760" "7509" "4206" "9854" ...
```

## License

Copyright © 2015 neruner

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
