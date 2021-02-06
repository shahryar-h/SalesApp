# salesApp


A Clojoure application that loads three databases (customer, product and sale databases). The application interface gives the user options to get information such as customer’s profile, sales status, customer and product total sales and product information. improved the program by error handling and preventing the program from loading databases with wrong information(like a negative number for customer’s age). I improved the program’s performance by preventing using loops and only used the default functions of Cloujure.

### prerequisites:
you need to have [Clojure](https://clojure.org/guides/getting_started) installed on your machine

### to run the program:

1. clone the project to a directory on your machine:

```
git clone git@github.com:shahryar-h/salesApp.git

```
then navigate to the project folder in terminal. and run:

```
clj sales.clj
```
the menu will appear:
![Menu](/Images/menu.png)

## Program Example:
we can count how many purchases of milk we have:


![product](/Images/example.png)
