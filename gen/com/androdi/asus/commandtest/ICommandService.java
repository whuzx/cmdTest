/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: G:\\学习\\EverBox\\Android\\Android项目学习\\CommandTest\\src\\com\\androdi\\asus\\commandtest\\ICommandService.aidl
 */
package com.androdi.asus.commandtest;
public interface ICommandService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.androdi.asus.commandtest.ICommandService
{
private static final java.lang.String DESCRIPTOR = "com.androdi.asus.commandtest.ICommandService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.androdi.asus.commandtest.ICommandService interface,
 * generating a proxy if needed.
 */
public static com.androdi.asus.commandtest.ICommandService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.androdi.asus.commandtest.ICommandService))) {
return ((com.androdi.asus.commandtest.ICommandService)iin);
}
return new com.androdi.asus.commandtest.ICommandService.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_doCommand:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.util.List<java.lang.String> _arg1;
_arg1 = new java.util.ArrayList<java.lang.String>();
int _result = this.doCommand(_arg0, _arg1);
reply.writeNoException();
reply.writeInt(_result);
reply.writeStringList(_arg1);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.androdi.asus.commandtest.ICommandService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public int doCommand(java.lang.String command, java.util.List<java.lang.String> result) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(command);
mRemote.transact(Stub.TRANSACTION_doCommand, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
_reply.readStringList(result);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_doCommand = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public int doCommand(java.lang.String command, java.util.List<java.lang.String> result) throws android.os.RemoteException;
}
