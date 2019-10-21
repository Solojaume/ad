using System;
using Gtk;
using CGtk;
using Serpis.Ad;
using System.Collections.Generic;
using System.Data;
using MySql.Data.MySqlClient;
using System.Reflection;

public partial class MainWindow : Gtk.Window
{
    private static IDbConnection dbConnection = new MySqlConnection(
    "server=localhost;database=dbprueba;uid=root;pwd=sistemas;ssl-mode=none"
    );
    public MainWindow() : base(Gtk.WindowType.Toplevel) {
        Build();
        dbConnection.Open();

        IList<Categoria> categorias = new List<Categoria>();
        IDbCommand dbCommand = dbConnection.CreateCommand();
        dbCommand.CommandText = "select * from categoria";
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

        TreeViewHelper.Fill(treeView, new string[] { "Id", "Nombre" },categorias);

        newAction.Activated += (sender, e) => new CategoriaWindow (dbConnection,null);

        editAction.Activated += (sender, e) => {
            new CategoriaWindow(dbConnection,TreeViewHelper.GetId(treeView));
        };
        refreshAction.Activated += (sender, e) =>
            TreeViewHelper.Fill(treeView, new string[] { "Id", "Nombre" }, categorias);

        refreshStateActions();
        treeView.Selection.Changed += (sender, e) => refreshStateActions();
    }

    protected void OnDeleteEvent(object sender, DeleteEventArgs a) {
        Application.Quit();
        dbConnection.Close();
        a.RetVal = true;
    }

    private void refreshStateActions() {
        bool hasSelectedRows = treeView.Selection.CountSelectedRows() > 0;
        editAction.Sensitive = hasSelectedRows;
        deleteAction.Sensitive = hasSelectedRows;
    }
}
