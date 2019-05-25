package com.company;

public class Cell
{
    boolean [][] cells;
    boolean [][] cells2;
    int row;
    int col;
    public Cell(int r, int c)
    {
        row = r;
        col = c;
        cells = new boolean[row][col];
        cells2 = new boolean[row][col];
        StdDraw.setScale(0,row);
        for (int x = 0; x < row; x++)
        {
            for (int y = 0; y <col; y++)
            {
                StdDraw.square(x, y, 1);
            }
        }
        StdDraw.show(1000);
    }

    public void background()
    {
        StdDraw.setScale(0,row);

        for (int x = 0; x < row; x++)
        {
            for (int y = 0; y <col; y++)
            {
                StdDraw.square(x, y, 1);
            }
        }
    }

    public void updateCopy()
    {
        for(int r = 0; r<row; r++){
            for(int c = 0; c<col; c++){
                cells2[r][c]= cells[r][c];
            }
        }
    }

    public void initializeCells()
    {
        //all cells are false
        //put in some alive cell
        cells[row-1-row/2][col/2] = true;
        cells[row-1-row/2][col/2-1] = true;
        cells[row-1-row/2][col/2+1] = true;
        cells[row-1-row/2+1][col/2+1] = true;
        cells[row-1-row/2+2][col/2] = true;
        updateCopy();
    }

    public void fillCells()
    {
        for(int r=row-1;r>=0;r--){
            for(int c=0;c<col;c++){
                if(cells[r][c]==true){
                    StdDraw.filledSquare(c-.5, r-.5, 0.5);
                }
            }
        }
    }

    public int findAliveCell()
    {
        int count = 0;
        for(int r=0;r<row;r++){
            for(int c=0;c<col;c++){
                if(cells2[r][c]){
                    count++;
                }
            }
        }
        return count;
    }

    public int numOfNeigh(int r, int c)
    {
        int num =0;
        //on the top
        if(r==0){
            if(c==0){
                if(cells2[r][c+1])num++;
                if(cells2[r+1][c])num++;
                if(cells2[r+1][c+1])num++;
            }else if(c==col-1){
                if(cells2[r][c-1])num++;
                if(cells2[r+1][c-1])num++;
                if(cells2[r+1][c])num++;
            }else{
                if(cells2[r][c-1])num++;
                if(cells2[r][c+1])num++;
                if(cells2[r+1][c-1])num++;
                if(cells2[r+1][c])num++;
                if(cells2[r+1][c+1])num++;
            }
        }else if(c==0){
            if(r==row-1){
                if(cells2[r-1][c])num++;
                if(cells2[r-1][c+1])num++;
                if(cells2[r][c+1])num++;
            }else{
                if(cells2[r-1][c])num++;
                if(cells2[r-1][c+1])num++;
                if(cells2[r][c+1])num++;
                if(cells2[r+1][c])num++;
                if(cells2[r+1][c+1])num++;
            }
        }else if(r==row-1){
            if(c == col-1){
                if(cells2[r-1][c])num++;
                if(cells2[r-1][c-1])num++;
                if(cells2[r][c-1])num++;
            }else{
                if(cells2[r][c+1])num++;
                if(cells2[r][c-1])num++;
                if(cells2[r-1][c])num++;
                if(cells2[r-1][c+1])num++;
                if(cells2[r-1][c-1])num++;
            }
        }else if(c==col-1){
            if(cells2[r-1][c])num++;
            if(cells2[r+1][c])num++;
            if(cells2[r-1][c-1])num++;
            if(cells2[r+1][c-1])num++;
            if(cells2[r][c-1])num++;
        }else{
            if(cells2[r-1][c]) num++;
            if(cells2[r-1][c+1]) num++;
            if(cells2[r][c+1]) num++;
            if(cells2[r+1][c+1]) num++;
            if(cells2[r+1][c]) num++;
            if(cells2[r+1][c-1]) num++;
            if(cells2[r][c-1]) num++;
            if(cells2[r-1][c-1]) num++;
        }
        return num;

    }

    public void test(int r, int c)
    {
        if(cells2[r][c]){
            if(r==0)cells[r][c]=false;
            //live cell with no neighbor
            if(numOfNeigh(r, c)==0) cells[r][c]=false;
            //live cell with one exact neighbor
            if(numOfNeigh(r, c)==1)cells[r][c]=false;
            //live cell with more than 3 live neighbor
            if(numOfNeigh(r, c)>3)cells[r][c]=false;
        }else{
            if(numOfNeigh(r, c)==3){
                cells[r][c]=true;
            }
        }
    }

    public void addAlive(int r, int c)
    {
        cells[r+1][c+1]=true;
        StdDraw.clear();
        background();
        fillCells();
        StdDraw.show();
    }

    public void play()
    {
        initializeCells();
        fillCells();
        while(findAliveCell()>0){
            updateCopy();
            if(StdDraw.isMousePressed()){
                while(!StdDraw.isKeyPressed(40)){
                    if(StdDraw.isMousePressed()){
                        addAlive((int)StdDraw.mouseY(),(int)StdDraw.mouseX());
                    }
                }
            }
            for(int r=0;r<row;r++){
                for(int c=0;c<col;c++){
                    test(r,c);
                }
            }
            StdDraw.clear();
            background();
            fillCells();
            StdDraw.show(100);
        }
    }

    public void print(boolean[][] mat)
    {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }

    }


}
