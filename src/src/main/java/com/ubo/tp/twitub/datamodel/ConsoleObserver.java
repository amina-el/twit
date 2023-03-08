package main.java.com.ubo.tp.twitub.datamodel;

public class ConsoleObserver implements IDatabaseObserver{

	@Override
	public void notifyTwitAdded(Twit addedTwit) {
		System.out.println("add twit");
		System.out.println();
		
	}

	@Override
	public void notifyTwitDeleted(Twit deletedTwit) {
		System.out.println("delete twit");
	}

	@Override
	public void notifyTwitModified(Twit modifiedTwit) {
		// TODO Auto-generated method stub
		System.out.println("modify twit");
		
	}

	@Override
	public void notifyUserAdded(User addedUser) {
		// TODO Auto-generated method stub
		System.out.println("add user" + addedUser);
		
	}

	@Override
	public void notifyUserDeleted(User deletedUser) {
		// TODO Auto-generated method stub
		System.out.println("delete user");
		
	}

	@Override
	public void notifyUserModified(User modifiedUser) {
		// TODO Auto-generated method stub
		System.out.println("modify user");
		
	}

}
