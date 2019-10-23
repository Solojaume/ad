using System;
using System.Data;
using Serpis.Ad;

namespace CGtk
{
    public partial class CategoriaWindow : Gtk.Window
    {
        public CategoriaWindow(string title) : base(title) {
        }

        public CategoriaWindow(IDbConnection dbConnection, object id) :
                base(Gtk.WindowType.Toplevel) {
            this.Build();

            buttonOK.Clicked += (sender, e) =>
            {
                string nombre = entryName.Text;
                IDbCommand dbCommand = dbConnection.CreateCommand();
                if (id is null) {
                    dbCommand.CommandText = string.Format("insert into categoria(nombre) values(@nombre)");
                    DBCommmandHelper.AddParameter(dbCommand, "nombre", nombre);
                    dbCommand.ExecuteNonQuery();
                    Destroy();
                }
                else {
                    dbCommand.CommandText = "update categoria set nombre = @nombre where id=@id";
                    DBCommmandHelper.AddParameter(dbCommand, "nombre", nombre);
                    DBCommmandHelper.AddParameter(dbCommand, "id", id);
                    dbCommand.ExecuteNonQuery();
                }
            };

            buttonCancel.Clicked += (sender, e) =>  Destroy(); 
        }
    }
}
