using System;

namespace CArticulo
{
    class MainClass
    {
        public static void Main(string[] args)
        {
            Menu.Create("Menu Categorías")
                .Add("1-Nuevo", nuevo)
                .Add("2-Editar", editar)
                .Add("3-Borrar", borrar)
                .Add("4-Listar", listar)
                .ExitWhen("0 - Salir")
                .Loop();
            
        }

        private static void nuevo() {
            Console.WriteLine("Ha entrado en nuevo");
            Console.ReadLine();
        }

        private static void editar() {
            Console.WriteLine("Ha entrado en editar");
            Console.ReadLine();
        }

      

        private static void borrar() {
            Console.WriteLine("Ha entrado en borrar");
            Console.ReadLine();
        }

       


        private static void listar() {

        }


    }
}
