part of 'users_cubit.dart';

@immutable
abstract class UsersState {}

class UsersInitial extends UsersState {}

class UsersLoaded extends UsersState {
  final List<User>users;
  UsersLoaded(this.users);
}
