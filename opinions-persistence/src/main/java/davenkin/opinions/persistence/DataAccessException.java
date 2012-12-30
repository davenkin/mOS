package davenkin.opinions.persistence;

public class DataAccessException extends Exception
{
    public DataAccessException(Exception e)
    {
        super(e);
    }

    public DataAccessException()
    {
        // TODO Auto-generated constructor stub
    }

    public DataAccessException(String message)
    {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public DataAccessException(Throwable cause)
    {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    public DataAccessException(String message, Throwable cause)
    {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }
    //To change body of created methods use File | Settings | File Templates.

}
