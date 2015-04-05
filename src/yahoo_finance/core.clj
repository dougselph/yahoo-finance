(ns yahoo-finance.core
  (:require [net.cgrand.enlive-html :as h]
            [clojure.string :as s]))

(defrecord Company [code exchange name description])
(def root-url "http://stocks.finance.yahoo.co.jp")
(def base-url (str root-url  "/stocks/qi/?js="))
(def aiueo (s/split (str "あいうえお" "かきくけこ" "さしすせそ" "たちつてと"
                         "なにぬねの" "はひふへほ" "まみむめも" "やゆよ"
                         "らりるれろ" "わ")
                    #""))

(defn fetch-url [url]
  (h/html-resource (java.net.URL. url)))

(defn aiueo-url [s]
  (str base-url (. java.net.URLEncoder encode s)))

(defn- select-aiueo []
  (let [resource (fetch-url (aiueo-url (first aiueo)))]
    (h/select resource [:div.aiueo5 :> :table.yjS :> :tr :> :td :> :span])))

(defn count-companies []
  (reduce + (map #(Integer. (re-find #"\d+" (h/text %)))
                 (select-aiueo))))

(defn select-companies [resource]
  (h/select resource [:#listTable :> :table.yjS :> :tr.yjM]))

(defn parse-company [tr]
  (let [tds (h/select tr [:td])
        code (h/text (first tds))
        exchange (h/text (second tds))
        name (h/text (first (h/select (nth tds 2) [:a])))
        description (h/text (first (h/select (nth tds 2) [:span])))]
    (->Company code exchange name description)))

(defn get-companies-start-with [s]
  (let [resource (fetch-url (aiueo-url s))
        next-page-selector [:#listTable :> :div.yjListTab :> :p :> :span.listNext :> :a]]
    (loop [result (map parse-company (select-companies resource))
           rest-page (h/select resource next-page-selector)]
      (if (empty? rest-page)
        result
        (let [next-page (fetch-url (str root-url
                                        (:href (:attrs (first rest-page)))))]
          (recur (concat result (map parse-company (select-companies next-page)))
                 (h/select next-page next-page-selector)))))))

(defn get-all-companies []
  (flatten (map get-companies-start-with aiueo)))
