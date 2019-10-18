using System;
using Gtk;
using CGtk;
using Serpis.Ad;
using System.Collections.Generic;

public partial class MainWindow : Gtk.Window
{
    public MainWindow() : base(Gtk.WindowType.Toplevel) {
        Build();

        IList<Categoria> categorias = new List<Categoria>();
        categorias.Add( new Categoria(1, "cat 1"));
        categorias.Add(new Categoria(2, "cat 2"));


        TreeViewHelper.Fill(treeView, new string[] { "Id", "Nombre" },categorias);

        newAction.Activated += (sender, e) => new CategoriaWindow ();
        editAction.Activated += (sender, e) => {
            object value = TreeViewHelper.GetValue(treeView, "Nombre");
            Console.WriteLine("editAction Activated Nombre = " + value);
        };
    }

    protected void OnDeleteEvent(object sender, DeleteEventArgs a) {
        Application.Quit();
        a.RetVal = true;
    }
}
