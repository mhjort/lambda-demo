(ns lambda-demo.handler
  (:require [base64-clj.core :as base64]
            [clojure.java.io :as io])
  (:import [java.io File]
           [java.util UUID]
           [com.amazonaws.services.s3 AmazonS3Client]))

(defonce client (AmazonS3Client.))

(def bucket-name "TODO")

(defn- sort-input [event]
  (let [raw-rows (map #(-> % :kinesis :data base64/decode)
                 (:Records event))]
    (sort raw-rows)))

(defn- store-to-s3 [records]
  (let [temp-file (File/createTempFile "demo" nil nil)
        s3-object-key (str (UUID/randomUUID))]
    (.deleteOnExit temp-file)
    (with-open [wrtr (io/writer temp-file)]
      (doseq [line records]
        (.write wrtr (str line "\n"))))
    (.putObject client bucket-name s3-object-key temp-file)))

(defn process [event]
  (store-to-s3 (sort-input event)))
