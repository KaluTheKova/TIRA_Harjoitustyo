public class DeletedItem extends Item {
    private static DeletedItem entry = null;

    private DeletedItem() {
        super(null);
    }

    // Make an UNIQUE (different object ID) deleted item.
    public static DeletedItem getDeletedItem() {       
        if (entry == null) {
            entry = new DeletedItem();
        }
        return entry;
    }
}