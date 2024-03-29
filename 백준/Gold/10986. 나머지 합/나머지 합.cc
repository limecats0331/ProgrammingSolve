#include "bits/stdc++.h"

using namespace std;

int N, M;
typedef long long ll;

ll cnt[1010];
ll arr[1010101];

int main() {
    cin.tie(0);
    ios::sync_with_stdio(0);

    int n, m; cin >> n >> m;
    for(int i=1; i<=n; i++){
        cin >> arr[i];
        arr[i] += arr[i-1];
        arr[i] %= m;
        cnt[arr[i]]++;
    }

    ll ans = cnt[0];
    for(int i=0; i<m; i++){
        ll now = cnt[i];
        ans += now * (now - 1) / 2;
    }
    cout << ans;

    return 0;
}

