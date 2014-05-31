(ns deathrow-server.test.handler
  (:use clojure.test
        ring.mock.request
        deathrow-server.handler))

(deftest test-app
  (testing "random route"
    (let [response (app (request :get "/offenders/random"))]
      (is (= (:status response) 200))
      (is (= (re-matches #".*?application/json.*?" (get (:headers response) "Content-Type")) "application/json; charset=utf-8"))
      (is (not= (re-find #"lastStmtUrl" (:body response)) nil))))
  
  (testing "not-found route"
    (let [response (app (request :get "/invalid"))]
      (is (= (:status response) 404)))))
