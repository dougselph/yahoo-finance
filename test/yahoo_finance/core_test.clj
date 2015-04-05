(ns yahoo-finance.core-test
  (:require [clojure.test :refer :all]
            [yahoo-finance.core :refer :all]))

(deftest count-companies-test
  (is (= (count-companies) (count (get-all-companies)))))
