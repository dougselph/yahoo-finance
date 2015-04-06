(defproject yahoo-finance "0.2.0"
  :description "Parse Yahoo Finance"
  :url "https://github.com/neruner/yahoo-finance"
  :scm {:name "git"
        :url "https://github.com/neruner/yahoo-finance"}
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :signing {:gpg-key "E93EC3F7"}
  :deploy-repositories [["clojars" {:creds :gpg}]]
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [enlive "1.1.5"]])
