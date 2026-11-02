package space.tictac.web.model;


public class BoardDto {
    private int [][]arr;


    public BoardDto(){
        this.arr = new int[3][3];
    }

    public BoardDto(int [][]arr){
        this.arr = arr;
    }

    public int[][] getArr(){
        return this.arr;
    }

    public void setArr(int [][]arr){
        this.arr=arr;
    }

}
