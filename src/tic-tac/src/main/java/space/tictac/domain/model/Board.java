package space.tictac.domain.model;

public class Board {

    public static final int Empty=0;
    public static final int Player1 =1;//Крестики
    public static final int Player2 =2;//Нолики

    private int[][]arr;


    public int[][] getArr(){
        return this.arr;
    }

    public void setArr(int[][]arr){
        this.arr=arr;
    }

    public Board(){
        this.arr=new int[3][3];
    }

    public Board(int [][]arr){
        this.arr = arr;
    }
}
