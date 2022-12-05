# Crazy Sort


## How Does is work
This is a mixture of Integer Sort and Radix Sort.

We start by searching the maximum value of the list. This allow us to get the max number of characters of the biggest number. 

Insted of starting by the least significant value like the Radix Sort, we start from the most significant. (This is why we need to know the max number of chars).
All the smaller numbers will have a zero as the most significant value. 

Then instead of sorting with > and < operators, we put the values inside one of the 10 lists created at each iteration of the function. The single value that we are analyzing represents the index of the list where it will be inserted into (this is the same concept used in Integer Sort).
This way we don't have to worry abount having to many lists (as it may happen using Integer Sort). Also since we are starting from the most significant value, if there are really big and small numbers inside the initial list, they already get sorted. 

The sorting happens when the base step of the recursion insert the item into the sorted result list.


## Problems
- It doesnt work with negative values, since we only have 10 lists, we should have another 10 lists for the negative numbers and interate them from the -9 to the -1, since I want to put into the result list the values starting from the smaller ones to the biggest one.
- It is slow, since the 10 lists are deallocated and allocated everytime we add a new value. I already tried using arrays, but since they have a static dimension we can assign them the biggest value based on the size of the list we are sorting. The problem is that the memory usage is huge since I am occupating a lot of memory that I dont use (at every recursion 10 lists are created).
- It doesnt currently support float list sorting, but it shouldnt be hard to implement. Since we just need to keep the recursion going for the decimal values (skipping the value with the "." character).

With a better implementation this algorithm might be able to compete with Mergesort and Quicksort in terms of complexity.
Currently for a 1 million element list (tested with a really old 2 core 4 threads intel i5 laptop):
- Merge Sort / Quick Sort (from the Java Collections): ~850ms
- Crazy Sort: ~2400 ms









