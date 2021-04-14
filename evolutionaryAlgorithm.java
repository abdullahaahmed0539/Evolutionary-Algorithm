import java.util.Random;

public class evolutionaryAlgorithm {

    static Random rand = new Random();
    public static void main(String[] args) {
        int population [][] = new int [5][2];
        population[0][0]=1; population[0][1]=2;
        population[1][0]=-2; population[1][1]=3;
        population[2][0]=4; population[2][1]=-1;
        population[3][0]=5; population[3][1]=2;
        population[4][0]=-3; population[4][1]=3;
        int fitness[];
        int children_fitness[];
        int parent [][];
        int children [][];
        int max=-1;


        int i=1;
        int k=0;
        int index=-1;
        int min=10000;

        while(i<=20) {
            fitness = Fitness(population);
            parent=Binary_tournament(fitness,population);
            children=Cross_over(parent);
            children=mutation(children);
            children_fitness=Fitness(children);


            for(int j=0;j<children_fitness.length;j++){
                while(k<5){
                    if(fitness[k]<min){
                        min=fitness[k];
                        index=k;
                    }
                    k++;
                }


                if(children_fitness[j]>min){
                    population[index][0]=children[j][0];
                    population[index][1]=children[j][1];
                    fitness[index]=children_fitness[j];
                }
                k=0;
            }

            for(int j=0;j<fitness.length;j++){
                if(fitness[j]>max){
                    max=fitness[j];
                }
            }

            System.out.println("max fitness in itr "+i+"="+max);
            i++;
        }



    }

    private static int [] Fitness(int [][] arr){
        int fitness[]= new int [5];
        for(int i=0;i<arr.length;i++){
            fitness[i]=(int) ( Math.pow(arr[i][0],2)+ Math.pow(arr[i][1],2) );
        }
        return fitness;
    }

    private static int [][] Binary_tournament(int [] fitness,int [][]population){
        int binary_tournament_competitors [][] = new int [8][3];
        int parent [][]=new int [4][2];
        int rand_int1;
        int n=-1;

        for(int i=0;i<binary_tournament_competitors.length;i++){
            rand_int1 = rand.nextInt(5);

            if (rand_int1!=n){
                n=rand_int1;
            }else{
                while(rand_int1==n){
                    rand_int1=rand.nextInt(5);
                }
                n=rand_int1;
            }

            binary_tournament_competitors[i][0]=population[rand_int1][0];
            binary_tournament_competitors[i][1]=population[rand_int1][1];
            binary_tournament_competitors[i][2]=fitness[rand_int1];
        }

        int j=0;
        for (int i=0;i<binary_tournament_competitors.length;i+=2){
           if(binary_tournament_competitors[i][2]>binary_tournament_competitors[i+1][2]){
               parent[j][0]=binary_tournament_competitors[i][0];
               parent[j][1]=binary_tournament_competitors[i][1];
           }else {
               parent[j][0]=binary_tournament_competitors[i+1][0];
               parent[j][1]=binary_tournament_competitors[i+1][1];
           }
           j++;
        }
        return parent;
    }

    private static int [][] Cross_over(int [][] parent){
        int children [][] = new int [4][2];

        for (int i=0;i<parent.length;i+=2){
            children[i][0]=parent[i][0];
            children[i][1]=parent[i+1][1];
            children[i+1][0]=parent[i+1][0];
            children[i+1][1]=parent[i][1];
        }
        return children;
    }

    private static int [][] mutation(int [][] children){
        float rand_int2;
        int swap;

        for(int i=0;i<children.length;i++){
            rand_int2= (float) Math.random();
            if(rand_int2<=0.5){
                swap=children[i][0];
                children[i][0]=children[i][1];
                children[i][1]=swap;
            }
        }
        return children;
    }

}
