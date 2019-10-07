To run the program:
First compile the Driver.java file
Next you can exicute the Driver.class file using no added exicution tags or any combinaiton of the following tags.
"--popCap #" the top # individuals that the algorithm will pick from.
"--muteRate #" The #% chance that a child will have a mutation.
"--probSize #" The number # of queens in the n queens problem.
"--maxGen #" The max number # of generations the algorithm will create before failure.
"--chilCap #" the number # of children the algorithm will create per generation.

An example exicution might look like: 
"java Driver.class --popCap 20 --muteRate 20 --probSize 100 --maxGen 1000000 --chilCap 100"