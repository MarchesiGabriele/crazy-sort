import numpy as np
import time

TIMES = False 

# IDEA: per i numeri negativi, divido array iniziale in due array, positivi e negativi, poi applico l'algoritmo 
# su entrambi gli array separatamente e poi unisco i due risulati. (quello dei negativi lo tratto 
# come se fosse un array di numeri positivi)

def crazy_sort(nums):
    start = time.time()
    # prendo lunghezza del più grande valore 
    maxVal = max(nums)
    maxLen = len(str(maxVal))
    #print("Max LEN: ", maxLen)

    # Matrice 10 x k
    # Riempio la matrice di liste vuote
    t1 = time.time()
    mat = []
    for x in range(10):
        mat.append([])
        for y in range(maxLen):
            mat[x].append([])
    t2 = time.time()
    if TIMES:
        print("Creazione matrice: ", t2-t1)

    # Riempio prima colonna della matrice
    t1 = time.time()
    for x in nums:
        strx = str(x)
        if len(strx) < maxLen:
            mat[0][0].append(x)
        else:
            firstLeftNumber = int(strx[0]) # cifra più significativa
            mat[firstLeftNumber][0].append(x)
    t2 = time.time()
    if TIMES:
        print("Prima Colonna: ", t2-t1)

    t1 = time.time()
    for x in range(maxLen-1): # scorro colonne
        for y in range(10): # scorro righe
            #print("colonna", x, " riga", y)
            if len(mat[y][x]) > 1:
                #print("VAL: ", mat[y][x])
                #print(len(mat[y][x]))
                for e in mat[y][x]:
                    #idx = (maxLen-(x+1))%len(str(e)) 
                    if len(str(e)) < maxLen-(x+1):
                        #print("METTO", e, " IN ", 0, " ", x+1)
                        mat[0][x+1].append(e)
                    else:
                        idx = (e%pow(10, maxLen-(x+1)))//pow(10, maxLen-(x+2))
                        print(e)
                        print(idx)
                        #print("METTOA", e, " IN ", int(str(e)[idx]), " ", x+1)
                        #mat[int(str(e)[idx])][x+1].append(e)
                        mat[idx][x+1].append(e)

                # dato che fare la remove costa O(n), pulisco l'intera lista dopo che
                # ho ridistribuito gli elementi
                mat[y][x].clear()
    t2 = time.time()
    if TIMES:
        print("Ordinamento: ", t2-t1)
    
    # Preparo array risultato, ho un array per ciascun numero di index
    result = []
    for m in range(maxLen):
        result.append([])

    print(mat)


    t1 = time.time() 
    # Leggo risultato finale
    for x in range(maxLen-1, -1, -1): # scorro colonne
        for y in range(10): # scorro righe
            for e in mat[y][x]:
                print(e)
                result[len(str(e))-1].append(e)
    t2 = time.time() 
    if TIMES:
        print("Lettura risultato: ", t2-t1)


    # merge delle liste
    final_res = []
    for x in result:
        final_res += x

    end = time.time() 
    if TIMES:
        print("Tempo Totale: ", end-start)

    return final_res


if __name__ == "__main__":
    #test = [1,4,7,2,8,461,9,10,460,0,0,22,1,122, 122, 121, 120, 99,199, 1998,1997]
    test = np.random.randint(2, 100000, size=10)
    test = [1000, 1250, 2220, 1225]
    print(test)

    crazy_sorted = crazy_sort(test)
    print("CRAZY: \n", crazy_sorted)

    t1 = time.time()
    test.sort()
    print("PYTHON: \n", test)
    t2 = time.time()
    print("Python Sort: ", t2-t1)

    if (crazy_sorted == test).all():
        print("SORT IS CORRECT")





