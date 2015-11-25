(ns lambda-demo.handler
  (:require [lambda-demo.env :refer [setup]]
            [base64-clj.core :as base64]
            [clojure.java.io :as io])
  (:import [java.io File]
           [java.util UUID]
           [com.amazonaws.services.s3 AmazonS3Client]))

(defonce client (AmazonS3Client.))

(defn- sort-input [event]
  (let [raw-rows (map #(-> % :kinesis :data base64/decode)
                 (:Records event))]
    (sort raw-rows)))

(defn- write-to-temp-file [records]
  (let [temp-file (File/createTempFile "demo" nil nil)]
    (.deleteOnExit temp-file)
    (with-open [wrtr (io/writer temp-file)]
      (doseq [line records]
        (.write wrtr (str line "\n"))))
    temp-file))

(defn- store-to-s3 [records]
  (let [s3-object-key (str (UUID/randomUUID))
        temp-file (write-to-temp-file records)]
    (.putObject client (:s3-bucket setup) s3-object-key temp-file)))

(defn process [event]
  (store-to-s3 (sort-input event)))

; This is how to test locally
;(process {:Records [{:kinesis {:data (base64/encode "soem text")}}]})
