
def filter_wallets2(wallet: dict) -> bool:
    try:
        if (wallet['address'].lower() in WALLETS):
            return True
    except:
        return False
    return False


def load_wallets() -> None:
    global WALLETS
    with open(file_wallets, 'r') as file:
        WALLETS = [row.strip().lower() for row in file]


def edit_dates1(wallets: list) -> None:
    for wallet in wallets:
        for i in wallet:
            if (i in (['ibt'])):
                wallet[i] = wallet[i][:19]
            if (i == 'amount_usd' and wallet[i] != None):
                wallet[i] = round(wallet[i],2)
                
def edit_dates2(wallets: list) -> None:
    for wallet in wallets:
        for i in wallet:
            if (i == 'eth_total' and wallet[i] != None):
                wallet[i] = f'{round(wallet[i],4)} ({round(wallet[i]*1800,2)})'
            if (i == 'usd_total' and wallet[i] != None):
                wallet[i] = round(wallet[i],2)
                
def get_filtered_wallets(data_file: str) -> list:
    with open(data_file, 'r') as file:
        data = json.load(file)

    all_wallet_info = data['data']['get_execution']['execution_succeeded']['data']
    
