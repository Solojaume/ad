using System;
using System.Data;
namespace CMySql
{
    public class DBCommmandHelper
    {
        public static void AddParameter(IDbCommand dbCommand, string name, object value)
        {
            IDataParameter dbDataParameter = dbCommand.CreateParameter();
            dbDataParameter.ParameterName = name;
            dbDataParameter.Value = value;
            dbCommand.Parameters.Add(dbDataParameter);//cat33
        }
    }
}

