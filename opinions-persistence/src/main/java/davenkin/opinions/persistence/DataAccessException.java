package davenkin.opinions.persistence;

public class DataAccessException extends Exception
{
    public DataAccessException(Exception e)
    {
        super(e);
    }

    public DataAccessException()
    {
        super();
    }

    public DataAccessException(String message)
    {
        super(message);
    }

    public DataAccessException(Throwable cause)
    {
        super(cause);
    }

    public DataAccessException(String message, Throwable cause)
    {
        super(message, cause);
    }

}
