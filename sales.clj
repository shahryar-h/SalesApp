; initializations
(def customerDirectory "cust.txt")
(def productDirectory "prod.txt")
(def salesDirectory "sales.txt")


; makes a sorted map out of a list using map function

(defn makeMap
  [data]
  (def result (map (fn [arg] (clojure.string/split arg #"\|")) data))
  (def res2 (map (fn [arg] (assoc [1 (rest arg)] 0 (first arg))) result))
  (into (sorted-map) res2 )
  )

; read file from system directory using reduce function
(defn readProductFile [file]
   (with-open [rdr (clojure.java.io/reader file)]
   (reduce conj [] (line-seq rdr))))

; create a table out of associated file using map function
(defn creatTable [directory]
    (def fileContent (readProductFile directory))
    (def items (makeMap fileContent))
    (map (fn [[key value]] [ key":" (map #(str "\"" % "\"" ) value) ]) items)   
)


; reading and creating customer and product tables
(def productsTable (creatTable productDirectory) )
(def customerTable (creatTable customerDirectory) )


; gets a map and print its content recursively 
(defn printItemsOf [yeMap] 
    (if (not-empty yeMap)
        (do 
        (println (first yeMap))
        (printItemsOf (rest yeMap))
        )
    )
)

; database Initiation
(def productsFileContent (readProductFile productDirectory))
(def mapOfProducts (makeMap productsFileContent))

(def customerFileContent (readProductFile customerDirectory))
(def mapOfCustomers (makeMap customerFileContent))

(def salesFileContent (readProductFile salesDirectory))
(def mapOfsales (makeMap salesFileContent))

(def productTxt (readProductFile productDirectory))
(def productsMap (makeMap productTxt))

(def customerTxt (readProductFile customerDirectory))
(def customerMap (makeMap customerTxt))

;calculates all sales of a customer using map and reduce function
(defn calculateCustomerSales [arg]
   (def a (subvec (vec arg) 1))
    (def subb(map str a))
    (def ssubb (map str subb))
    (def final (map (fn [arg] (Double/valueOf arg)) ssubb))
    (reduce * final)   
)


(defn makeMap2
  [data]
  (def result (map (fn [arg] (clojure.string/split arg #"\|")) data))
  (def res2 (map (fn [arg] (assoc [1 (nth arg 1)] 0 (first arg))) result))
  (into (sorted-map) res2 )
  )


(defn creatTable2 [directory]
    (def fileContent (readProductFile directory))
    (makeMap2 fileContent)
    ;(map (fn [[key value]] [ key":" (map #(str "\"" % "\"" ) value) ]) items)   
)

(def mapByCustomerName (creatTable2 customerDirectory))

(defn test22 [c] 
    
    (concat (get mapOfProducts (nth c 1)) (nth c 2))
)

(defn checkMapp[saleMap customerMap customersName]

    (def nameId (map first (filter (comp #{customersName} last) mapByCustomerName)))
    
    (if (not-empty nameId)
    (do
        (def nnn (nth nameId 0))
        (def listOfitems (conj [] nnn))
        (def listOfitems (conj listOfitems customersName))
        (def ddd (vals saleMap))
        (def filteredCX (filter (fn [x] (= (nth x 0) nnn)) ddd))
        (def listOfitems (conj listOfitems (map (fn [x] (test22 x)) filteredCX)))
        (def one (vec (subvec listOfitems 2)))
        (def one (nth one 0))
        (def res1 (map calculateCustomerSales one))
        (def resfinal (reduce + res1))
        (println (str customersName ":" resfinal))

    )(println "Customer Dose not exist in the records!")
    )
    
)


; recursively goes through sales list, convert its values and print the modified list.
(defn printSalesList [theMap] 
    
    (if (not-empty theMap)
        (do
        (def mapItem (first theMap))
        ;(def keyha (keys mapItem))
        (def k (first mapItem))
        (def valll (rest mapItem))
        (def v (first valll))
        (print k": ")
        ; (println )
        (let [customerID (str (nth v 0))] 
            
            (let [customersss (get mapOfCustomers customerID "missing")]
            (let [customerName (str "\"" (nth customersss 0) "\"")]
                (print customerName)
            )
            )  
        )
        (print " ")
        (let [productID (str (nth v 1))] 

            (let [products (get mapOfProducts productID "missing")]
            (let [productName (str "\"" (nth products 0) "\"")]
                (print productName)
            )
            ) 
        
        )
        (print " ")
        (println (str (nth v 2)))
        (printSalesList (rest theMap))
        
        )
    )



    )



(defn makeMap5
  [data]
  (def result (map (fn [arg] (clojure.string/split arg #"\|")) data))


  (def res2 (map (fn [arg] (assoc [1 (first arg)] 0 (first (rest arg)))) result))
  (into (sorted-map) res2 )
  )


(defn creatTable5 [directory]

    (def fileContent (readProductFile directory))
    (makeMap5 fileContent)

)

(defn sada [argu]
    (vec (map (fn [x] (Integer/valueOf (str x))) argu))
)

(defn makeMap6
  [data pId]

    (def result (map (fn [arg] (clojure.string/split arg #"\|")) data))

    (def intResults (map (fn [x] (sada x)) result))
    (def pairOfProductNumber (map (fn [x] (subvec x 2)) intResults))
    
    ; (reduce (fn [x] () pairOfProductNumber))
    (def filterdres (filter (fn [x] (= (Integer/valueOf (str pId)) (nth x 0))) pairOfProductNumber))

    (def vectorOfFilteredResults (vec filterdres))
    ;(def resultFF (map + filterdres))
    
    (def xweqweqw (map (fn [x] (nth x 1)) vectorOfFilteredResults ))
    (reduce + xweqweqw)
  )


(defn creatTable6 [directory productIds]

    (def fileContent (readProductFile directory))
    
    (makeMap6 fileContent productIds)

)



(defn productCalculator [pName]
    (def productMapByName (creatTable5 productDirectory))
    (def productID (get productMapByName pName "missing"))
    (if (= productID "missing") 
        (println "the Product Dose not belong to our products List ")
        (do 
            (def aaaaaaa (creatTable6 salesDirectory productID ))
            (println aaaaaaa)
        )
    )   
)

; displays menu recursively until the user stop it by chosing option 6
(defn printMenu [] 
(println "*** Sales Menu ***")
(println "------------------")
(println "1. Display Customer Table")
(println "2. Display Product Table")
(println "3. Display Sales Table")
(println "4. Total Sales for Customer")
(println "5. Total Count for Product")
(println "6. Exit")
(println "Enter an option?")
(let [selectedOption (read-line)]
  (case selectedOption
    "1" (printItemsOf customerTable)
    "2" (printItemsOf productsTable)
    "3" (printSalesList mapOfsales)
    "4" (do (println "Please Provisde the Customer's Name: ") (let [nameOfCustomer (read-line)] (checkMapp mapOfsales customerMap nameOfCustomer)))
    "5" (do (println "Please Provisde the Product's Name: ") (let [nameOfProduct (read-line)] (productCalculator nameOfProduct)))
    "6" (println "Good Bye")
    (println "Invalid Selection - Options are 1-6 Only!")
    )
  (if (not= selectedOption "6")
    (printMenu))  
))


(printMenu)