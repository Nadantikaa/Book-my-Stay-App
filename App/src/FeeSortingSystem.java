import java.util.Arrays;

class Client {
    String id;
    int riskScore;
    double accountBalance;

    public Client(String id, int riskScore, double accountBalance) {
        this.id = id;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return id + "(" + riskScore + ")";
    }
}

public class RiskRanking {

    public static void bubbleSortAsc(Client[] clients) {
        int n = clients.length;
        int swaps = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (clients[j].riskScore > clients[j + 1].riskScore) {
                    Client temp = clients[j];
                    clients[j] = clients[j + 1];
                    clients[j + 1] = temp;
                    swaps++;
                }
            }
        }
        System.out.println("Bubble (asc) Swaps: " + swaps);
    }

    public static void insertionSortDesc(Client[] clients) {
        int n = clients.length;
        for (int i = 1; i < n; i++) {
            Client key = clients[i];
            int j = i - 1;
            while (j >= 0 && (clients[j].riskScore < key.riskScore ||
                    (clients[j].riskScore == key.riskScore && clients[j].accountBalance < key.accountBalance))) {
                clients[j + 1] = clients[j];
                j--;
            }
            clients[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        Client[] clients = {
                new Client("clientC", 80, 1000),
                new Client("clientA", 20, 2000),
                new Client("clientB", 50, 1500)
        };

        System.out.print("Input: ");
        System.out.println(Arrays.toString(clients));

        bubbleSortAsc(clients);
        System.out.println("Result: " + Arrays.toString(clients));

        insertionSortDesc(clients);
        System.out.println("Insertion (desc): " + Arrays.toString(clients));

        System.out.print("Top 3 risks: ");
        for (int i = 0; i < Math.min(3, clients.length); i++) {
            System.out.print(clients[i] + (i == 2 ? "" : ", "));
        }
        System.out.println();
    }
}