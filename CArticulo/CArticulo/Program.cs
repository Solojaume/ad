using System;
using Serpis.Ad;
using System.Data;
using MySql.Data.MySqlClient;
namespace CArticulo
{
    class MainClass
    {
        private static IDbConnection dbConnection = new MySqlConnection(
              "server=localhost;database=dbprueba;uid=root;pwd=sistemas;ssl-mode=none"
           );
        private static int obtion = 0;

        public static void Main(string[] args)
        {
            dbConnection.Open();
            Menu.Create("Menu Aticulo")
                .Add("1-Nuevo", nuevo)
                .Add("2-Editar", editar)
                .Add("3-Borrar", borrar)
                .Add("4-Listar", listar)
                .ExitWhen("0 - Salir")
                .Loop();
            dbConnection.Close();
        }

        private static void nuevo() {
            IDbCommand dbCommand = dbConnection.CreateCommand();
            Console.WriteLine("Introduce el nombre del articulo: ");
            string nombre = Console.ReadLine();
            Console.WriteLine("Introduce el precio del articulo: ");
            string precio = Console.ReadLine();
            Console.WriteLine("Introduce el categoria del articulo: ");
            string categoria = Console.ReadLine();

            dbCommand.CommandText = string.Format("insert into Articulo(nombre, precio, categoria) values(@nombre,@precio,@categoria)");
            DBCommmandHelper.AddParameter(dbCommand, "nombre", nombre);
            DBCommmandHelper.AddParameter(dbCommand, "precio", precio);
            DBCommmandHelper.AddParameter(dbCommand, "categoria", categoria);
            dbCommand.ExecuteNonQuery();
        }

        private static void editar() {
            Console.WriteLine("Ha entrado en editar");

            IDbCommand dbCommand = dbConnection.CreateCommand();
            obtion = 1;
            listar();//Aquí se muestra

            //Console.WriteLine("Introduce el id del articulo que quieras editar:");
            //DBCommmandHelper.AddParameter(dbCommand, "id", Console.ReadLine());
            //Menu.Create("Menu editar")
              // .Add("1-Nombre", () =>
              // {
              //     Console.WriteLine("Introduce el nuevo nombre del articulo:");
              //     dbCommand.CommandText = "Update articulo set nombre = @nombre, " +
              //                       "where id= @id";
              //     DBCommmandHelper.AddParameter(dbCommand, "nombre", Console.ReadLine());
              // })
              //.Add("2-Precio", () =>
              //{
              //    Console.WriteLine("Introduce el nuevo nombre del articulo:");
              //    dbCommand.CommandText = "Update articulo set precio = @precio, " +
              //                      "where id= @id";
              //    DBCommmandHelper.AddParameter(dbCommand, " precio ", Console.ReadLine());
              //})
              //.Add("3-Borrar", borrar);
            
            dbCommand.CommandText = "Update articulo set nombre = @nombre, " +
            	                     "precio = @precio, categoria = @categoria "+ 
                                          "where id= @id";

            Console.WriteLine("Introduce el id del articulo que quieras editar:");
            DBCommmandHelper.AddParameter(dbCommand, "@id", Console.ReadLine());

            Console.WriteLine("Introduce el nuevo nombre del articulo:");
            DBCommmandHelper.AddParameter(dbCommand, "@nombre", Console.ReadLine());

            Console.WriteLine("Introduce el nuevo precio del articulo:");
            DBCommmandHelper.AddParameter(dbCommand, "@precio", float.Parse(Console.ReadLine()));

            Console.WriteLine("Introduce el nuevo categoria del articulo:");
            DBCommmandHelper.AddParameter(dbCommand, "@categoria", int.Parse(Console.ReadLine()));
            dbCommand.ExecuteNonQuery();


        }

      

        private static void borrar() {
            IDbCommand dbCommand = dbConnection.CreateCommand();
            obtion = 1;
            listar();//Aquí se muestra

            Console.WriteLine("Introduce el id del articulo que quieras borrar:");
            string id = Console.ReadLine();

            dbCommand.CommandText = "Delete from articulo where id = @id";
            DBCommmandHelper.AddParameter(dbCommand, "id", id);
            dbCommand.ExecuteNonQuery();

        }

       


        private static void listar() {
            if (obtion == 0)
                Console.WriteLine("Has entrado al modo listar");
            Console.WriteLine("Muestro todos los articulos de la lista");
            IDbCommand dbCommand = dbConnection.CreateCommand();
            dbCommand.CommandText = "select * from articulo ";
            IDataReader dataReader = dbCommand.ExecuteReader();


            while (dataReader.Read()) {
                Console.WriteLine("id={0} nombre={1} precio={2} categoria={3}", dataReader["id"], dataReader["nombre"], dataReader["precio"], dataReader["categoria"]);
            }
            obtion = 0;
            dataReader.Close();
        }



    }
}
