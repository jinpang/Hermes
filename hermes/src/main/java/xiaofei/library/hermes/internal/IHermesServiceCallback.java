/**
 *
 * Copyright 2016 Xiaofei
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package xiaofei.library.hermes.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public interface IHermesServiceCallback extends IInterface {

    abstract class Stub extends Binder implements IHermesServiceCallback {

        private static final String DESCRIPTOR = "xiaofei.library.hermes.internal.IHermesServiceCallback";

        public Stub() {
            this.attachInterface(this, DESCRIPTOR);
        }

        public static IHermesServiceCallback asInterface(IBinder obj) {
            if ((obj==null)) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (((iin!=null)&&(iin instanceof IHermesServiceCallback))) {
                return ((IHermesServiceCallback)iin);
            }
            return new Proxy(obj);
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case INTERFACE_TRANSACTION:
                    reply.writeString(DESCRIPTOR);
                    return true;
                case TRANSACTION_callback:
                    data.enforceInterface(DESCRIPTOR);
                    CallbackMail _arg0;
                    if ((0!=data.readInt())) {
                        _arg0 = CallbackMail.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    Reply _result = this.callback(_arg0);
                    reply.writeNoException();
                    if ((_result!=null)) {
                        reply.writeInt(1);
                        _result.writeToParcel(reply, Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
            }
            return super.onTransact(code, data, reply, flags);
        }

        private static class Proxy implements IHermesServiceCallback {

            private IBinder mRemote;

            Proxy(IBinder remote) {
                mRemote = remote;
            }

            @Override
            public IBinder asBinder() {
                return mRemote;
            }

            public String getInterfaceDescriptor() {
                return DESCRIPTOR;
            }

            @Override
            public Reply callback(CallbackMail mail) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                Reply _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if ((mail!=null)) {
                        _data.writeInt(1);
                        mail.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    mRemote.transact(Stub.TRANSACTION_callback, _data, _reply, 0);
                    _reply.readException();
                    if ((0!=_reply.readInt())) {
                        _result = Reply.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }
        }

        static final int TRANSACTION_callback = (IBinder.FIRST_CALL_TRANSACTION + 0);
    }

    Reply callback(CallbackMail mail) throws RemoteException;

}