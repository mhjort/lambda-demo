# lambda-demo

A Clojure AWS Lambda demo.

Reads events from Kinesis stream and stores them in S3.

## Installation

* Clone the repo
* Setup Lambda environment in `project.clj`.
  You have to at least change the S3 bucket (and possibly region if you don't want to use Ireland datacenter)
* Create Kinesis stream
* Open `src/lambda-demo/env.clj` and configure setup based on your environment
* Install Lambda by running

  $ lein lambda install test

* This will create a Lambda function with name LambdaDemo with basic setup.
* Configure Lambda function to use Kinesis stream as an event source.
* Enter some test data via repl

  $ lein repl


```clojure
(use 'lambda-demo.kinesis-util)

(insert-test-data "some-data" 50)
```

* Check your S3 bucket. There should be a bunch of sorted files.
* Profit!!!

If you make any changes to to Lambda code you can redeploy your Lambda function by running:

```
./update-lambda <your-region>
```

## License

Copyright © 2015-2016 Markus Hjort

Distributed under the Eclipse Public License.
