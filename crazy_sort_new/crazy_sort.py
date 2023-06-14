import numpy as np

# IDEA: per i numeri negativi, divido array iniziale in due array, positivi e negativi, poi applico l'algoritmo 
# su entrambi gli array separatamente e poi unisco i due risulati. (quello dei negativi lo tratto 
# come se fosse un array di numeri posiitivi)

def crazy_sort(nums):
    # prendo lunghezza del più grande valore 
    maxVal = max(nums)
    maxLen = len(str(maxVal))
    print("Max LEN: ", maxLen)

    # Matrice 10 x k
    # Riempio la matrice di liste vuote
    mat = []
    for x in range(10):
        mat.append([])
        for y in range(maxLen):
            mat[x].append([])

    """
    for y in range(len(mat)):
        for x in range(len(mat[y])):
            mat[y][x] = np.array([]) 
    """


    # Riempio prima colonna della matrice
    for x in nums:
        strx = str(x)
        if len(strx) < maxLen:
            mat[0][0].append(x)
        else:
            firstLeftNumber = int(strx[0]) # cifra più significativa
            mat[firstLeftNumber][0].append(x)

    for x in range(maxLen-1): # scorro colonne
        print(mat)
        print("NEW INDEX: ", x+1)
        for y in range(10): # scorro righe
            print("colonna", x, " riga", y)
            if len(mat[y][x]) > 1:
                for e in mat[y][x]:
                    #idx = i-(maxLen-len(str(e)))
                    idx = (maxLen-(x+1))%len(str(e)) 
                    print("idx", idx)
                    len(mat)
                    print(mat[y][x])
                    print(e)
                    if len(str(e)) < maxLen-(x+1):
                        print("METTO", e, " IN ", 0, " ", x+1)
                        mat[0][x+1].append(e)
                    else:
                        print("METTOA", e, " IN ", int(str(e)[idx]), " ", x+1)
                        mat[int(str(e)[idx])][x+1].append(e)

                # dato che fare la remove costa O(n), pulisco l'intera lista dopo che
                # ho ridistribuito gli elementi
                mat[y][x].clear()

    # Preparo array risultato, ho un array per ciascun numero di index
    result = []
    for m in range(maxLen):
        result.append([])

    print(mat)

    # Leggo risultato finale
    for x in range(maxLen-1, -1, -1): # scorro colonne
        for y in range(10): # scorro righe
            for e in mat[y][x]:
                print(e)
                result[len(str(e))-1].append(e)


    print(result)



if __name__ == "__main__":
    test = [1,4,7,2,8,9,10, 460,0,0,22,1,122]
    crazy_sort(test)