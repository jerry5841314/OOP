## 說明 ##
給定兩圓A, B圓心與半徑,以及要測的點之數量，請分辨這些點是在圓A或圓B上，或是在兩圓的交集中，或是在兩圓之外，並列出各自點的數量<br>
注意： 在圓周上的點也視為在圓內。<br>
`Formula: x^2 + y^2 = r^2` <br>

<br>

## Input Format ##
The first line contains three integers: `x1,y1,r1`, indicating the coordinates and radius of the first circle.
The second line contains three integers : `x2,y2,r2`, indicating the coordinates and radius of the second circle.<br> 
The third line contains one integer `n`, indicating the number of input points below.
For the following `n` lines, each contains two integers `x,y`, which are the coordinates of the points. 

第一行包含三個整數：`x1,y1,r1`，表示第一個圓的坐標和半徑。<br>
第二行包含三個整數：`x2,y2,r2`，表示第二個圓的坐標和半徑。<br>
第三行包含一個整數 `n`，表示下面輸入點的個數。<br>
對於接下來的 `n` 行，每行包含兩個整數 `x,y`，它們代表點的坐標。



## Output Format ##
Output共有四行，依序代表在兩圓交集、第一個圓、第二個圓以及不在兩圓以內的點的數目。<br>
注意： 在圓周上的點也視為在圓內。

## Sample Input ##
```
10 -1 8 
7 10 9
7
-1 7
7 9
6 8
3 2
7 2
4 4
5 -2
```
## Sample Output ##
```
3
1
3
0
```

## Hint ##
可以使用 `input().split()` 來切割輸入。 例如 `x, y, r = map(int, input().split())`。

![image](https://github.com/jerry5841314/OOP/blob/main/problem%202/pic.png)
