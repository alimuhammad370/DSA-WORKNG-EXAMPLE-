import java.util.*;

public class Main{
    static class Route{
        int destination;
        String type;

        Route(int destination,String type){
            this.destination = destination;
            this.type = type;
        }
    }

    static class State{
        int city;
        int days;

        State(int city,int days){
            this.city = city;
            this.days = days;
        }
    }

    public static int findMinimumDays(int cities,List<int[]> aerialRoutes){

        //city connections
        Map<Integer,List<Route>> graph = new HashMap<>();
        for(int i=0;i<cities;i++){
            graph.put(i,new ArrayList<>());
        }

        //add aerial routes
        for(int[] route : aerialRoutes){
            graph.get(route[0]).add(new Route(route[1],"air"));
        }

        //add road routes
        for(int i=0;i<cities-1;i++){
            for(int j=1;j<=6 && i+j<cities;j++){
                graph.get(i).add(new Route(i+j,"road"));
            }
        }

        //initialize a queue for BFS traversal
        Queue<State> queue = new LinkedList<>();
        queue.add(new State(0,0)); //start at city 0 with 0 days

        //map to store the min days
        Map<Integer,Integer> minDays = new HashMap<>();
        minDays.put(0,0); //starting city takes 0 days to reach

        //minResult to store the min days
        int minResult = Integer.MAX_VALUE;

        while(!queue.isEmpty()){
            State current = queue.poll();
            int city = current.city;
            int days = current.days;

            //if reach the destination ,update the result.
            if(city == cities - 1){
                minResult = Math.min(minResult,days);
                continue;
            }

            //xplore all routes from the current city.
            for(Route route : graph.get(city)){
                int nextCity = route.destination;
                int nextDays = days;

                //for air travel, days remain same.
                if(route.type.equals("air")){
                    nextDays = days;
                }
                //for road travel, days incremented by 1.
                else if(route.type.equals("road")){
                    nextDays = days + 1;
                }

                //revisit a city if it can be reached in fewer days or if it hasnt been visited yet.
                if(!minDays.containsKey(nextCity) || nextDays < minDays.get(nextCity)){
                    minDays.put(nextCity,nextDays);
                    queue.add(new State(nextCity,nextDays));
                }
            }
        }
        return minResult == Integer.MAX_VALUE ? -1 : minResult;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of test cases: ");
        int numCases = scanner.nextInt();

        for(int i = 0;i < numCases; i++){
            System.out.print("Enter number of cities: ");
            int cities = scanner.nextInt();

            System.out.print("Enter number of aerial routes: ");
            int numAirRoutes = scanner.nextInt();
            List<int[]> aerialRoutes= new ArrayList<>();

            System.out.println("Enter aerial routes (source destination): ");
            for(int j = 0; j < numAirRoutes; j++){
                int src = scanner.nextInt();
                int dest = scanner.nextInt();
                aerialRoutes.add(new int[]{src,dest});
            }

            int result = findMinimumDays(cities,aerialRoutes);

            System.out.println("Minimum Days = "+result);
        }
        scanner.close();
    }
}