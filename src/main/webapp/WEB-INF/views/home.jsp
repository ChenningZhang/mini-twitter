<title>Mini-Twitter Home Page</title>

<div class="container">
	<table class="table table-striped">
		<thead>
			<tr>
				<th>Path</th>
				<th>query</th>
				<th>Description</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>/tweets</td>
				<td>userId, search(optional)</td>
				<td>Displays the tweets of the user with given userId(include self-tweets and people being followed by the user). An optional search param can be used to filter posts.</td>
			</tr>
			<tr>
				<td>/follow</td>
				<td>currUserId, followUserId</td>
				<td>If the request went through successfully, user with id=currUserId will start following user with id=followUserId, and it will return a success message. Otherwise an error message will show.</td>
			</tr>
			<tr>
				<td>/unfollow</td>
				<td>currUserId, unfollowUserId</td>
				<td>If the request went through successfully, user with id=currUserId will stop following user with id=unfollowUserId, and it will return a success message. Otherwise an error message will show.</td>
			</tr>
			<tr>
				<td>/followers-and-following-users</td>
				<td>userId</td>
				<td>Displays the names of the user's followers and people being followed by the user.</td>
			</tr>
			<tr>
				<td>/login</td>
				<td>userId</td>
				<td>If the request went through successfully, user with id=userId will be logged in and a success message shows. Otherwise an error message shows. ALL THE ABOVE REQUESTS THROW UNAUTHORIZED EXCEPTIONS IF USER WITH ID=USERID/CURRUSERID NOT LOGGED IN.</td>
			</tr>
		</tbody>
	</table>
</div>