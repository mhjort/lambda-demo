(defproject lambda-demo "0.1.0-SNAPSHOT"
  :description "AWS Lambda in Clojure demo"
  :url "https://github.com/mhjort/lambda-demo"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [cheshire "5.5.0"]
                 [base64-clj "0.1.1"]
                 [uswitch/lambada "0.1.0"]]
  :aot [lambda-demo.core])
