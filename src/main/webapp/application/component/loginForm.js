var LoginForm = React.createClass({
	handleSubmit: function(e) {
		e.preventDefault();
		var userEmail = React.findDOMNode(this.refs.userEmail).value.trim();
		var userPassword = React.findDOMNode(this.refs.userPassword).value.trim();
		if (!userPassword || !userEmail) {
			return;
		}

		this.props.onLoginSubmit({userEmail: userEmail, userPassword: userPassword});

		React.findDOMNode(this.refs.userEmail).value = '';
		React.findDOMNode(this.refs.userPassword).value = '';
		return;
	},
	render: function() {
		return (
			<form className="loginForm" onSubmit={this.handleSubmit}>
				<input type="email" placeholder="이메일" ref="userEmail" />
				<input type="password" placeholder="비밀번호" ref="userPassword" />
				<input type="submit" value="로그인" />
			</form>
		);
	}
});

var LoginBox = React.createClass({
	handleLoginSubmit: function(loginInfo) {
		$.ajax({
			url: this.props.url,
			dataType: 'json',
			type: 'POST',
			data: loginInfo,
			success: function(data) {
				alert("login success : " + data);
			}.bind(this),
			error: function(xhr, status, err) {
				alert("login fail");
				console.error(this.props.url, status, err.toString());
			}.bind(this)
		});
	},
	render: function() {
		return (
			<div className="LoginBox">
				<h1>로그인</h1>
				<LoginForm onLoginSubmit={this.handleLoginSubmit} />
			</div>
		);
	}
});

React.render(
	<LoginBox url="/hello/getData" />,
	document.getElementById('loginForm')
);