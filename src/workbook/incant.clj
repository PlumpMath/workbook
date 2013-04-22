(ns workbook.incant)

;; Incanter <http://incanter.org> is an R-like platform for Clojure.
;; We're going to have a play with analysing and visualizing some data.

(use '(incanter core stats charts))

;; view a function-plot
(view (function-plot sin -4 4))

;; view a histogram of the normal distribution
(view (histogram (sample-normal 1000)))

;; what is the distribution of the entropy fn from our decision tree homework?
(view (bar-chart (range 100) (for [i (range 100)] (workbook.dec-tree/entropy (/ i 100)))))

;; Maybe we want to graph some data that's embedded in these web pages:
;; http://en.wikipedia.org/wiki/List_of_countries_by_life_expectancy
;; http://en.wikipedia.org/wiki/List_of_countries_by_income_equality

;; a tiny subset of gini ineqality data
(def inequality {"Australia" 0.336, "Israel" 0.371, "Slovenia" 0.236})

;; a tiny subset of life expectancy data, male/female pairs.
(def expectancy {"Australia" [79.2 83.8], "Israel" [79.2 82.9], "Slovenia" [75.9 82.5]})

;; scatter-plot ineqality vs female life expectancy, taking a moment
;; to appreciate the notation. N.B. map'ing expectancy using the keys
;; of inequality means we only plot data points for which we have
;; both.
(def plot (scatter-plot (vals inequality) (map (comp second expectancy) (keys inequality))))

;; add text labels so we know which countries are which
(doseq [k (keys inequality)]
  (add-text plot (inequality k) (second (expectancy k)) k))

;; show the plot
(view plot)

;; Your homework: get the rest of the data from above Wiki links, do
;; any cleansing or normalization required, plot the results. BUT! you
;; should do so trying to minimize the number of manual steps involved
;; by using the full power of emacs + clojure. Your deliverable must
;; show your work (intermediate states of the data, a narrative of
;; what you did to get from A->B, any code you wrote as part of the
;; process).
