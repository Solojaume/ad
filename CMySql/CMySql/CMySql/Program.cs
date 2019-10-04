﻿using System;
using System.Data;
using MySql.Data.MySqlClient;
using Serpis.Ad;
namespace CMySql
{
    class MainClass
    {
        private static IDbConnection dbConnection = new MySqlConnection(
                "server=localhost;database=dbprueba;uid=root;pwd=sistemas;ssl-mode=none"
             );
        public static void Main(string[] args)
        {
            Console.WriteLine("Acceso a dbprueba");
            dbConnection.Open();
            InsertValue();
            ShowAll();
            ShowMetaInfo();
            dbConnection.Close();
        }

        public static void ShowAll()
        {
            IDbCommand dbCommand = dbConnection.CreateCommand();
            dbCommand.CommandText = "select * from categoria";
            IDataReader dataReader = dbCommand.ExecuteReader();
            while (dataReader.Read())
            {
                Console.WriteLine("id={0} nombre={1}", dataReader["id"], dataReader["nombre"]);
            }

            dataReader.Close();
        }

        public static void InsertValue()
        {
            IDbCommand dbCommand = dbConnection.CreateCommand();
            Console.WriteLine("Introduce el nombre de la categoria: ");
            string nombre = Console.ReadLine();
            dbCommand.CommandText = string.Format("insert into categoria(nombre) values(@nombre)");

            //IDataParameter dbDataParameter = dbCommand.CreateParameter();
            //dbDataParameter.ParameterName = "nombre";
            //dbDataParameter.Value = nombre;
            //dbCommand.Parameters.Add(dbDataParameter);//cat33
            DBCommmandHelper.AddParameter(dbCommand, "nombre", nombre);

            dbCommand.ExecuteNonQuery();
        }

        public static void ShowMetaInfo()
        {
            IDbCommand dbCommand = dbConnection.CreateCommand();
            dbCommand.CommandText = "select * from categoria";
            IDataReader dataReader = dbCommand.ExecuteReader();

            Console.WriteLine("FieldCoun={0}", dataReader["id"], dataReader.FieldCount);
            for (int index = 0; index < dataReader.FieldCount; index++)
            {
                Console.WriteLine("Field {0} = {1,-15} Type= {2}", dataReader.GetName(index), dataReader.FieldCount);
            }

            dataReader.Close();
        }


    }
}