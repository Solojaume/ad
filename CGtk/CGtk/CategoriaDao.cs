using System;
using System.Collections.Generic;
using System.Collections;
using System.Collections.Generic;
using System.Data;
using Serpis.Ad;


namespace CGtk
{
    public class CategoriaDao
    {
        private static string selectAll = "select * from categoria order by id";
        private static  IDbConnection dbConnection = App.Instance.DbConnection;
        private static IEnumerable GetAll() {
            IList<Categoria> categorias = new List<Categoria>();
            IDbCommand dbCommand = dbConnection.CreateCommand();
            dbCommand.CommandText = selectAll;
            IDataReader dataReader = dbCommand.ExecuteReader();
            while (dataReader.Read()) {
                //OPCIÓN 1
                categorias.Add(new Categoria((ulong)dataReader["id"], (string)dataReader["nombre"]));

                //OPCIÓN 2
                //Categoria categoria = new Categoria();
                //categoria.Id = (ulong)dataReader["id"];
                //categoria.Nombre = (string)dataReader["nombre"];

                //OPCIÓN 3
                //Type type = typeof(Categoria);
                //Categoria categoria = Activator.CreateInstance<Categoria>();
                //type.GetProperty("id").SetValue(categoria, dataReader["id"]);
                //type.GetProperty("Nombre").SetValue(categoria, dataReader["nombre"]);
                //categorias.Add(categoria);
            }
            dataReader.Close();
            //categorias.Add( new Categoria(1, "cat 1"));
            //categorias.Add(new Categoria(2, "cat 2"));
            //categorias.Add(new Categoria(3, "cat 3"));
            return categorias;
        }

        private static string select = "select * from categoria where id = @id";
        private static Categoria Load (object id) {
            Categoria categoria = new Categoria();
            IDbCommand dbCommand = App.Instance.DbConnection.CreateCommand();
            dbCommand.CommandText = select;
            DBCommmandHelper.AddParameter(dbCommand, "id", id);
            IDataReader dataReader = dbCommand.ExecuteReader();
            dataReader.Read();
            categoria.Id = (ulong)id;
            categoria.Nombre = (string)dataReader["nombre"];
            dataReader.Close();
            return categoria;
        }

    }
}
