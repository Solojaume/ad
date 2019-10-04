using System;
namespace CArticulo
{
    public class Menu
    {
        public static Menu Create(string menuLabel)
        {
            return new Menu();
        }
        public Menu Add(string label,Action action)
    }
}
