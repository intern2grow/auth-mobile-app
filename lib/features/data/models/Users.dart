
class User {
  String? _username;
  String? _password;

  User({
    String? username,
    String? password,
  }) : _username = username,
        _password = password;

  User.fromJson(Map<String, dynamic> json) {
    _username = json['username'];
    _password = json['password'];
  }
}
