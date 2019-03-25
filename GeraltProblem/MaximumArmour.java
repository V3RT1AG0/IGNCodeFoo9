

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaximumArmour
{
    static int maxArmour = 0;
    static String best = "";

    static void GenerateTree(List<List<Inventory>> Lists, List<String> result, int depth, String current, int sumArmour, int sumCost)
    {

        if (depth == Lists.size())
        {
            if (sumCost < 300)  //Check if sumCost is less than 300 or not
            {
                if (sumArmour > maxArmour)
                {
                    maxArmour = sumArmour;
                    best = current;
                }
            }
            return;
        }

        for (int i = 0; i < Lists.get(depth).size(); ++i)
        {
            Inventory item = Lists.get(depth).get(i);
            GenerateTree(Lists, result, depth + 1, current + item.name + ",", sumArmour + item.armour_value, sumCost + item.cost);
        }
    }

    public static void main(String[] args)
    {
        List<Inventory> inventories = new ArrayList<>();
        for (Object[] arry : Constants.inventory_array)
        {
            inventories.add(new Inventory((String) arry[0], (String) arry[1], (int) arry[2], (int) arry[3]));
        }
        List<Inventory> helmets = new ArrayList<>();
        List<Inventory> legging = new ArrayList<>();
        List<Inventory> chest = new ArrayList<>();
        List<Inventory> boots = new ArrayList<>();
        for (Inventory item : inventories)
        {
            switch (item.type)
            {
                case "Helmet":
                    helmets.add(item);
                    break;
                case "Leggings":
                    legging.add(item);
                    break;
                case "Chest":
                    chest.add(item);
                    break;
                case "Boots":
                    boots.add(item);
                    break;
            }
        }
        List<List<Inventory>> inventory2 = new ArrayList<>(Arrays.asList(helmets, legging, chest, boots, inventories));
        List<String> list = new ArrayList<>();
        GenerateTree(inventory2, list, 0, "", 0, 0);
        System.out.println(best+" Total Armor->"+maxArmour);

    }
}

class Inventory
{
    String type, name;
    int cost, armour_value;

    Inventory(String type, String name, int cost, int armour_value)
    {
        this.type = type;
        this.name = name;
        this.cost = cost;
        this.armour_value = armour_value;
    }
}
